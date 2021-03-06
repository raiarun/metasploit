package msfgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;

/**
 * Window for searching for modules by name or details
 * @author scriptjunkie
 */
public class SearchWindow extends MsfFrame {
	public List modules; //list of type, name actor
	private RpcConnection rpcConn;
	protected DefaultListModel mod;
    /** Creates new form SearchWindow */
    public SearchWindow(RpcConnection rpcConn) {
		super("Module search window");
		this.rpcConn = rpcConn;
        initComponents();
		loadSavedSize();
		modules = new ArrayList(600);
		mod =new DefaultListModel();
		resultList.setModel(mod);
		searchBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String typed = searchBox.getEditor().getItem().toString();
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					doAction(typed);
					return;
				}
				if (evt.getKeyChar() == KeyEvent.CHAR_UNDEFINED || typed.equals(searchBox.getItemAt(0).toString()))
					return;
				//display items
				searchBox.removeAllItems();
				searchBox.addItem(typed);
				for (int i = 0; i < modules.size(); i++)
					if (((Object[])modules.get(i))[1].toString().contains(typed))
						searchBox.addItem(((Object[])modules.get(i))[1]);
				((javax.swing.JTextField) (searchBox.getEditor().getEditorComponent())).select(typed.length(),
						typed.length());
				searchBox.hidePopup();
				searchBox.showPopup();
			}
		});
    }

	/** starts handler for named item, whether exploit, payload, or auxiliary */
	private boolean doAction(String typed) {
		for (int i = 0; i < modules.size(); i++) {
			Object[] info = (Object[])modules.get(i);
			if (info[1].toString().equals(typed)) {
				((ActionListener) info[2]).actionPerformed(null);
				return true;
			}
		}
		return false;
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeGroup = new javax.swing.ButtonGroup();
        detailGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        exploitsRadioButton = new javax.swing.JRadioButton();
        auxiliaryRadioButton = new javax.swing.JRadioButton();
        payloadsRadioButton = new javax.swing.JRadioButton();
        searchBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        descriptionRadioButton = new javax.swing.JRadioButton();
        referenceRadioButton = new javax.swing.JRadioButton();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList();
        launchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(msfgui.MsfguiApp.class).getContext().getResourceMap(SearchWindow.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        typeGroup.add(exploitsRadioButton);
        exploitsRadioButton.setSelected(true);
        exploitsRadioButton.setText(resourceMap.getString("exploitsRadioButton.text")); // NOI18N
        exploitsRadioButton.setActionCommand(resourceMap.getString("exploitsRadioButton.actionCommand")); // NOI18N
        exploitsRadioButton.setName("exploitsRadioButton"); // NOI18N

        typeGroup.add(auxiliaryRadioButton);
        auxiliaryRadioButton.setText(resourceMap.getString("auxiliaryRadioButton.text")); // NOI18N
        auxiliaryRadioButton.setActionCommand(resourceMap.getString("auxiliaryRadioButton.actionCommand")); // NOI18N
        auxiliaryRadioButton.setName("auxiliaryRadioButton"); // NOI18N

        typeGroup.add(payloadsRadioButton);
        payloadsRadioButton.setText(resourceMap.getString("payloadsRadioButton.text")); // NOI18N
        payloadsRadioButton.setActionCommand(resourceMap.getString("payloadsRadioButton.actionCommand")); // NOI18N
        payloadsRadioButton.setName("payloadsRadioButton"); // NOI18N

        searchBox.setEditable(true);
        searchBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "type name here" }));
        searchBox.setName("searchBox"); // NOI18N
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        detailGroup.add(descriptionRadioButton);
        descriptionRadioButton.setSelected(true);
        descriptionRadioButton.setText(resourceMap.getString("descriptionRadioButton.text")); // NOI18N
        descriptionRadioButton.setActionCommand(resourceMap.getString("descriptionRadioButton.actionCommand")); // NOI18N
        descriptionRadioButton.setName("descriptionRadioButton"); // NOI18N

        detailGroup.add(referenceRadioButton);
        referenceRadioButton.setText(resourceMap.getString("referenceRadioButton.text")); // NOI18N
        referenceRadioButton.setActionCommand(resourceMap.getString("referenceRadioButton.actionCommand")); // NOI18N
        referenceRadioButton.setName("referenceRadioButton"); // NOI18N

        searchField.setText(resourceMap.getString("searchField.text")); // NOI18N
        searchField.setName("searchField"); // NOI18N

        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        resultList.setName("resultList"); // NOI18N
        jScrollPane1.setViewportView(resultList);

        launchButton.setText(resourceMap.getString("launchButton.text")); // NOI18N
        launchButton.setName("launchButton"); // NOI18N
        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBox, 0, 501, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(exploitsRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(auxiliaryRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(payloadsRadioButton))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(descriptionRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(referenceRadioButton))
                    .addComponent(searchButton)
                    .addComponent(launchButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exploitsRadioButton)
                    .addComponent(auxiliaryRadioButton)
                    .addComponent(payloadsRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionRadioButton)
                    .addComponent(referenceRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(launchButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
		if ((evt.getModifiers() & ActionEvent.MOUSE_EVENT_MASK) != 0) {
			String typed = searchBox.getEditor().getItem().toString();
			doAction(typed);
		}
	}//GEN-LAST:event_searchBoxActionPerformed

	private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
		mod.removeAllElements();
		String type = typeGroup.getSelection().getActionCommand();
		String detail = detailGroup.getSelection().getActionCommand();
		String toSearch = searchField.getText().toLowerCase();

		//look through all modules of selected type.
		for(Object module : modules){
			Object[] modInfo = (Object[])module;
			if(!modInfo[0].equals(type))
				continue;

			try { //Get info
				Map info = (Map) rpcConn.execute("module.info", modInfo[0], modInfo[1]);
				if(detail.equals("reference")){
					Object references = info.get("references");
					if(references != null){
						List refArray = (List)references;
						for(int i = 0; i < refArray.size(); i++){
							List ref = (List)refArray.get(i);
							if(ref.get(1).toString().toLowerCase().contains(toSearch)
									|| (ref.get(0).toString().toLowerCase()+"-"+ref.get(1)).contains(toSearch)){
								mod.addElement(modInfo[1]+" - "+Rank.toString(info.get("rank")));
								break;
							}
						}
					}
				}else{
					if(info.get("name").toString().toLowerCase().contains(toSearch)
							|| info.get("description").toString().toLowerCase().contains(toSearch))
						mod.addElement(modInfo[1]+" - "+Rank.toString(info.get("rank")));
				}
			} catch (MsfException ex) {
				MsfguiApp.showMessage(rootPane, ex);
			}
		}
	}//GEN-LAST:event_searchButtonActionPerformed

	private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchButtonActionPerformed
		String type = typeGroup.getSelection().getActionCommand();
		String name = resultList.getSelectedValue().toString();
		for(Object module : modules){
			Object[] modInfo = (Object[])module;
			if(modInfo[0].equals(type) && name.contains(modInfo[1].toString())){
				Map info = (Map) rpcConn.execute("module.info", modInfo[0], modInfo[1]);
				if(name.equals(modInfo[1].toString()+" - "+Rank.toString(info.get("rank")))){
					((ActionListener) modInfo[2]).actionPerformed(null);
					break;
				}
			}
		}
	}//GEN-LAST:event_launchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton auxiliaryRadioButton;
    private javax.swing.JRadioButton descriptionRadioButton;
    private javax.swing.ButtonGroup detailGroup;
    private javax.swing.JRadioButton exploitsRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton launchButton;
    private javax.swing.JRadioButton payloadsRadioButton;
    private javax.swing.JRadioButton referenceRadioButton;
    private javax.swing.JList resultList;
    private javax.swing.JComboBox searchBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.ButtonGroup typeGroup;
    // End of variables declaration//GEN-END:variables

}
