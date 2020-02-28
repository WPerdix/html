package html;

public class Node {
	private String tag;
	private String text;
	private Node firstChild;
	private Node lastChild;
	private Node nextSibling;
	private Node previousSibling;
	private Node parent;
	
	public Node(String tag, String text) {
		this.tag = tag;
		this.text = text;
	}
	
	public void addChild(Node child) {
		if (lastChild == null) {
			firstChild = child;
			lastChild = child;
		}
		else {
			lastChild.nextSibling = child;
			child.previousSibling = lastChild;
			lastChild = child;
		}
		child.parent = this;
	}
	
	public void removeChild(Node child) {
		if (firstChild == child) {
			firstChild = firstChild.nextSibling;
			firstChild.previousSibling = null;
			if (firstChild == null)
				lastChild = null;
		}
		else if (child.nextSibling != null) 
			child.previousSibling.nextSibling = child.nextSibling; 
		else {
			lastChild = child.previousSibling;
			lastChild.nextSibling = null;
		}
		child.previousSibling = null;
		child.nextSibling = null;
		child.parent = null;
	}
	
	public void remove() {
		if (parent != null)
			parent.removeChild(this);
	}
	
	public String toString() {
		if (text != null)
			return text;
		String result = "<" + tag + ">";
		Node child = firstChild;
		while (child != null) {
			result += child.toString();
			child = child.nextSibling;
		}
		result += "</" + tag + ">";
		return result;
	}
}
