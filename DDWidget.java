import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.text.*;
import java.text.NumberFormat;

public class DDWidget {
	final static int ONE_MINUTE = 60 * 1000;
	final static int TIME_TO_SLEEP = 1 * ONE_MINUTE;

	JFrame frame;
	JPanel mainPanel;
	JLabel salesLabel;
	JLabel laborLabel;
	JLabel timestampLabel;

	public static void main(String[] args) {
		DDWidget app = new DDWidget();
		app.buildGUI();
		app.go();
	}

	public void buildGUI() {

		salesLabel = new JLabel("");
		laborLabel = new JLabel("");
		timestampLabel = new JLabel("");
		// timestampLabel customization
		timestampLabel.setHorizontalAlignment(JLabel.CENTER);
		timestampLabel.setFont(timestampLabel.getFont().deriveFont(Font.ITALIC, 11f));

		salesLabel.setText("Loading...");
		laborLabel.setText("Loading...");
		timestampLabel.setText("Loading...");

		frame = new JFrame("DD Widget");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		Box infoBox = new Box(BoxLayout.Y_AXIS);
		infoBox.add(salesLabel);
		//infoBox.add(Box.createVerticalGlue());
		infoBox.add(laborLabel);
		//infoBox.add(Box.createVerticalGlue());

		//Box timestampBox = new Box(BoxLayout.X_AXIS);
		//timestampBox.add(timestampLabel);

		background.add(BorderLayout.WEST, infoBox);
		//background.add(BorderLayout.SOUTH, timestampBox);
		background.add(BorderLayout.SOUTH, timestampLabel);

		frame.getContentPane().add(background);
		frame.pack();
		frame.setSize(150,100);
		frame.setVisible(true);
	}

	private void go() {
		float sales;
		int salesCount;
		float labor;
		float discounts;
		String salesLabelToolTipText;

		String timestampFormat = "M/d/yy h:mm:ss a";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(timestampFormat);
		String date;

		DDDataSummary data = new DDDataSummary();

		while(true) {
			date = simpleDateFormat.format(new Date());
			data.fetch();
			sales = Float.parseFloat(data.getSales());
			salesCount = Integer.parseInt(data.getSalesCount());
			labor = Float.parseFloat(data.getLabor());
			discounts = Float.parseFloat(data.getDiscounts());

			salesLabel.setText("Sales: " + NumberFormat.getCurrencyInstance().format(sales));
			salesLabelToolTipText = String.format("Orders: %,d", salesCount) 
				+ ", Discounts: " 
				+ NumberFormat.getCurrencyInstance().format(discounts);
			salesLabel.setToolTipText(salesLabelToolTipText);

			laborLabel.setText("Labor: " + String.format("%,.2f%%", labor));

			timestampLabel.setText(date);

			// sleep.
			try {
				Thread.sleep(TIME_TO_SLEEP);
			} catch(Exception ex) {}
		}


	}
}	
