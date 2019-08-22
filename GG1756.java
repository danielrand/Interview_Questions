import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class GG1756 {
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}
		new GraphGUI();
	}
}

class GraphGUI extends JFrame implements ActionListener {
	
	static GraphPicturePanel picture;
	static Container cPane; // refer4nce to content pane of the JFrame
	static TextArea datesInFileOrder, datesInSortedOrder; // Two text areas, one
	// to display the dates in the order they appear in the file, and one in the
	// sorted format.
	static JMenuBar menuBar; // menu bar that will contain the File menu
	static JRadioButton addVertex, addEdge, removeVertex, removeEdge, moveVertex;
	static JButton addAllEdges, connectedComponents, showCutVertices, help;
	static JPanel panel;

	public GraphGUI() {
		initializeGUI();
	}

	public static void addButton(JComponent b) {
		panel.add(b);
		panel.add(Box.createVerticalStrut(10));
	}

	public void initializeGUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setVisible(true);
		setTitle("Graph GUI");
		setLayout(new BorderLayout());
		panel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		cPane = getContentPane();
		addVertex = new JRadioButton("Add Vertex");
		addVertex.addActionListener(this);
		buttonGroup.add(addVertex);
		addEdge = new JRadioButton("Add Edge");
		addEdge.addActionListener(this);
		buttonGroup.add(addEdge);
		removeVertex = new JRadioButton("Remove Vertex");
		removeVertex.addActionListener(this);
		buttonGroup.add(removeVertex);
		removeEdge = new JRadioButton("Remove Edge");
		removeEdge.addActionListener(this);
		buttonGroup.add(removeEdge);
		moveVertex = new JRadioButton("Move Vertex");
		moveVertex.addActionListener(this);
		buttonGroup.add(moveVertex);
		addAllEdges = new JButton("Add All Edges");
		addAllEdges.setPreferredSize(new Dimension(100, 200));
		addAllEdges.addActionListener(this);
		connectedComponents = new JButton("Connected Components");
		connectedComponents.addActionListener(this);
		showCutVertices = new JButton("Show Cut Vertices");
		showCutVertices.addActionListener(this);
		help = new JButton("Help");
		help.addActionListener(this);
		addButton(addVertex);
		addButton(addEdge);
		addButton(removeVertex);
		addButton(removeEdge);
		addButton(moveVertex);
		addButton(addAllEdges);
		addButton(connectedComponents);
		addButton(showCutVertices);
		addButton(help);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton test = new JButton("Test");
		// JPanel panel2 = new JPanel();
		// panel2.setLayout(new BorderLayout());
		// panel2.add(new
		// JButton("Tesdfjasdkfjhaslfkjhalsfkjhlskjfhlaksjdhflaskjdhft"));
		cPane.add(panel, BorderLayout.WEST);
		// cPane.add(panel2,BorderLayout.CENTER);
		picture = new GraphPicturePanel();
		cPane.add(picture, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static boolean isAddVSelected() {
		return addVertex.isSelected();
	}

	public static boolean isAddESelected() {
		return addEdge.isSelected();
	}

	public static boolean isRemoveVSelected() {
		return removeVertex.isSelected();
	}

	public static boolean isRemoveESelected() {
		return removeEdge.isSelected();
	}

	public static boolean isMoveVSelected() {
		return moveVertex.isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Help")) {
			JOptionPane.showMessageDialog(null, "Here's How to use the JFrame:\nslkfjlskfjlkjf", "Help", 1);
		}
	}
}

class DrawableGraph {
	// adjacency matrix (rows and columns are vertices, and 1 indicates an edge
	// connecting them, 0 otherwise)
	ArrayList<ArrayList<Integer>> adjacencyMatrix;
	// array of locations in space corresponding to the order vertices
	// ArrayList <Point> locations;
	int numVertices;

	public DrawableGraph() {
		adjacencyMatrix = new ArrayList<>();
		// locations = new ArrayList<>();
		numVertices = 0;
	}

	public void addVertex() {
		// locations.add(new Point (x,y));
		numVertices++;
		// add a new column to the matrix by adding a 0 to each list
		for (ArrayList<Integer> v : adjacencyMatrix) {
			v.add(0);
		}
		// create new list for new vertex, will all values as 0
		ArrayList<Integer> newVertex = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			newVertex.add(0);
		}
		adjacencyMatrix.add(newVertex);
	}
	public void removeVertex(int index) {
		adjacencyMatrix.remove(index);
		for (ArrayList<Integer> v : adjacencyMatrix) {
			v.remove(index);
		}
		numVertices--;
	}
	public void addEdge(int vertexA, int vertexB) {
		if (vertexA >= adjacencyMatrix.size()) {
			throw new IllegalArgumentException("Vertex A is not in the graph");
		}
		if (vertexB >= adjacencyMatrix.get(vertexA).size()) {
			throw new IllegalArgumentException("Vertex B is not in the graph");
		}
		adjacencyMatrix.get(vertexA).set(vertexB, 1);
		adjacencyMatrix.get(vertexB).set(vertexA, 1);
	}

	public ArrayList<ArrayList<Integer>> getMatrix() {
		return adjacencyMatrix;
	}
}
class DrawableVertex {
	Point location;
	Color color;
	public DrawableVertex(Point loc, Color c) {
		location = loc;
		color = c;
	}
	public void drawVertex (Graphics g) {
		g.setColor(color);
		g.fillOval((int) location.getX(), (int) location.getY(), 10, 10);
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
class DrawableEdge { 
	DrawableVertex first, second;
	Color color;
	public DrawableEdge (DrawableVertex a, DrawableVertex b, Color c) {
		first = a;
		second = b;
		color = c;
	}
	public void drawEdge (Graphics g) {
		g.setColor(color);
		g.drawLine((int)first.getLocation().getX()+5, (int)first.getLocation().getY()+5,
				(int)second.getLocation().getX()+5, (int)second.getLocation().getY()+5);
	}
	public DrawableVertex getFirst() {
		return first;
	}
	public void setFirst(DrawableVertex first) {
		this.first = first;
	}
	public DrawableVertex getSecond() {
		return second;
	}
	public void setSecond(DrawableVertex second) {
		this.second = second;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
class GraphPicturePanel extends JPanel implements MouseListener {

	DrawableGraph graph;
	ArrayList <DrawableVertex> verticesToDraw;
	ArrayList <DrawableEdge> edgesToDraw;
	int indexOfSelected = -1;
	int finalIndex = -1; // index of vertex to connect edge to
	boolean isSelected = false;
	
	public GraphPicturePanel() {
		this.setPreferredSize(new Dimension(1090, 600));
		verticesToDraw = new ArrayList<>();
		edgesToDraw = new ArrayList<>();
		graph = new DrawableGraph();
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		for (DrawableVertex v : verticesToDraw) {
			v.drawVertex(g);
		}
		for (DrawableEdge e : edgesToDraw) {
			e.drawEdge(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (GraphGUI.isAddVSelected()) {
			Point point = new Point (e.getX()-5,e.getY()-7);
			verticesToDraw.add(new DrawableVertex(point,Color.BLACK));
			graph.addVertex();
		}
		else if (GraphGUI.isRemoveVSelected()) {
			for (int i = 0; i < verticesToDraw.size(); i++) {
				Point p = verticesToDraw.get(i).getLocation();
				int oldX = (int)p.getX();
				int oldY = (int)p.getY();
				int newX = e.getX()-5;
				int newY = e.getY()-7;
				if (Math.abs(newX-oldX) <= 7 && Math.abs(newY-oldY) <= 7) {
					verticesToDraw.remove(i);
					graph.removeVertex(i);
				}
			}
		}
		else if (GraphGUI.isMoveVSelected()) {
			boolean clickOnPoint = false;
			for (int i = 0; i < verticesToDraw.size(); i++) {
				DrawableVertex currentVertex = verticesToDraw.get(i);
				Point p = currentVertex.getLocation();
				int oldX = (int)p.getX();
				int oldY = (int)p.getY();
				int newX = e.getX()-5;
				int newY = e.getY()-7;
				if (Math.abs(newX-oldX) <= 7 && Math.abs(newY-oldY) <= 7) {
					clickOnPoint = true;
					if (!isSelected) {
						currentVertex.setColor(Color.RED);
						isSelected = true;
						indexOfSelected = i;
					}
					else if (i == indexOfSelected) {
						currentVertex.setColor(Color.BLACK);
						isSelected = false;
						indexOfSelected = -1;
					}
				break;
				}
			}
			if (isSelected && !clickOnPoint) {
				DrawableVertex currentVertex = verticesToDraw.get(indexOfSelected);
				currentVertex.setColor(Color.BLACK);
				currentVertex.setLocation(new Point(e.getX()-5,e.getY()-5));
				isSelected = false;
				indexOfSelected = -1;
			}
		}
		else if (GraphGUI.isAddESelected()) {
			boolean clickOnPoint = false;
			for (int i = 0; i < verticesToDraw.size(); i++) {
				DrawableVertex currentVertex = verticesToDraw.get(i);
				Point p = currentVertex.getLocation();
				int oldX = (int)p.getX();
				int oldY = (int)p.getY();
				int newX = e.getX()-5;
				int newY = e.getY()-7;
				if (Math.abs(newX-oldX) <= 7 && Math.abs(newY-oldY) <= 7) {
					clickOnPoint = true;
					if (!isSelected) {
						currentVertex.setColor(Color.RED);
						isSelected = true;
						indexOfSelected = i;
					}
					else if (i == indexOfSelected) {
						currentVertex.setColor(Color.BLACK);
						isSelected = false;
						indexOfSelected = -1;
					}
					else if (isSelected && i != indexOfSelected) {
						finalIndex = i; 
					}
				break;
				}
			}
			if (isSelected && finalIndex != -1) {
				DrawableVertex currentVertex = verticesToDraw.get(indexOfSelected);
				DrawableVertex finalVertex = verticesToDraw.get(finalIndex);
				graph.addEdge(indexOfSelected, finalIndex);
				currentVertex.setColor(Color.BLACK);
				edgesToDraw.add(new DrawableEdge(currentVertex, finalVertex, Color.BLACK));
				isSelected = false;
				indexOfSelected = -1;
				finalIndex = -1;
			}
		}
		repaint();
		System.out.println();
		for (ArrayList<Integer> v : graph.getMatrix()) {
			for (Integer i : v) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
