package com.sme.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 构造树
 * @author yao
 *
 */
public class TreeNode {

	private String id;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private List<TreeNode> children;
	private Map<String, Object> attributes;
	private String name;
	private long menuId;

	public TreeNode() {
		super();
	}
	
	public TreeNode(String id,long menuId, String name,String text, String iconCls, String state,
			Map<String, Object> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.menuId = menuId;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.checked = false;
		this.children = new ArrayList<TreeNode>();
		this.attributes = attributes;
	}

	public TreeNode(String id,long menuId, String name,String text, String iconCls, String state,boolean checked,
			Map<String, Object> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.menuId = menuId;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.checked = checked;
		this.children = new ArrayList<TreeNode>();
		this.attributes = attributes;
	}

	public TreeNode(String id, String text, String iconCls, String state,
			Map<String, Object> attributes) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.checked = false;
		this.children = new ArrayList<TreeNode>();
		this.attributes = attributes;
	}

	public TreeNode(String id, String text, String iconCls, String state,
			boolean checked, Map<String, Object> attributes) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.checked = checked;
		this.children = new ArrayList<TreeNode>();
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public TreeNode appendChild(TreeNode node) {
		this.children.add(node);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

}

