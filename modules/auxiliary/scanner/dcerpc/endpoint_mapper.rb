##
# $Id$
##

##
# This file is part of the Metasploit Framework and may be subject to 
# redistribution and commercial restrictions. Please see the Metasploit
# Framework web site for more information on licensing and terms of use.
# http://metasploit.com/framework/
##


require 'msf/core'


class Metasploit3 < Msf::Auxiliary

	# Exploit mixins should be called first
	include Msf::Exploit::Remote::DCERPC
	
	include Msf::Auxiliary::Report

	# Scanner mixin should be near last
	include Msf::Auxiliary::Scanner
	
	def initialize
		super(
			'Name'        => 'Endpoint Mapper Service Discovery',
			'Version'     => '$Revision$',
			'Description' => %q{
				This module can be used to obtain information from the 
				Endpoint Mapper service.
			},
			'Author'      => 'hdm',
			'License'     => MSF_LICENSE
		)
		
		deregister_options('RHOST')
		
		register_options(
			[
				Opt::RPORT(135)
			], self.class)		
	end

	# Obtain information about a single host
	def run_host(ip)	
		begin

			ids = dcerpc_endpoint_list()
			return if not ids
			name = nil
			ids.each do |id|
				next if not id[:prot]
				line = "#{id[:uuid]} v#{id[:vers]} "
				line << "#{id[:prot].upcase} "
				line << "(#{id[:port]}) " if id[:port]
				line << "(#{id[:pipe]}) " if id[:pipe]
				line << "#{id[:host]} " if id[:host]
				line << "[#{id[:note]}]" if id[:note]
				print_status(line)							
				if (id[:host] and id[:host][0,2] == "\\\\")
					name = id[:host][2..-1]
				end
				if id[:prot].downcase == "tcp" or id[:prot].downcase == "udp"
					report_service(:host => ip, :port => id[:port], :proto => id[:prot].downcase)
				end
			end
			report_host(:host => ip, :name => name) if name

			
		rescue ::Interrupt
			raise $!
		rescue ::Exception => e
			print_status("Error: #{e}")
		end
	end
	

end
