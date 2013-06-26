package Auxilium.BackEnd;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 * This class is used to make graphs on the system. It creates both pie charts and bar charts for each category, such as
 * the amound of tickets from each department, ticket open time, etc. 
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class graphs
{

	// Ticket By Department Charts Start
	public static JFreeChart ticketByDeptPie()
	{
		String title = "Ticket By Department";
		String[] deptIDs = Database.getDepartmentIDs();

		DefaultPieDataset result = new DefaultPieDataset();

		for (int i = 0; i < deptIDs.length; i++)
		{
			String key = Database.getDeptName(deptIDs[i]);
			int numTickets = Database.getDepartmentTickets(deptIDs[i].trim());

			if (numTickets > 0)
			{
				result.setValue(key, numTickets);

			}
		}

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				result, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	public static JFreeChart ticketByDeptBar()
	{
		String title = "Ticket By Department";
		String[] deptIDs = Database.getDepartmentIDs();

		int numDepartments = deptIDs.length;

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		String deptID = null;

		for (int i = 0; i < deptIDs.length; i++)
		{
			String key = deptIDs[i];
			int numTickets = Database.getDepartmentTickets(deptIDs[i].trim());

			if (numTickets > 0)
			{
				result.setValue(numTickets, "Department", key);

			}
		}
		JFreeChart chart = ChartFactory.createBarChart("Tickets By Department",
				"Department", "Number Of Tickets", result,
				PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}

	// Ticket By Department Charts End

	// Ticket Close Time PieChart Start
	public static JFreeChart closeTimePie()
	{
		String title = "Ticket Close Time";
		int[] closeTimes = Database.getTicketCloseTime();

		DefaultPieDataset result = new DefaultPieDataset();
		int oneDay = 0;
		int twoToFiveDay = 0;
		int moreThanFiveday = 0;

		for (int i = 0; i < closeTimes.length; i++)
		{
			int daysToClose = closeTimes[i];
			if (daysToClose < 2)
			{
				oneDay++;
			} else if (daysToClose > 1 && daysToClose < 6)
			{
				twoToFiveDay++;
			} else
			{
				moreThanFiveday++;
			}
		}

		result.setValue("One Day", oneDay);
		result.setValue("Two to Five Days", twoToFiveDay);
		result.setValue("More Than Five Days", moreThanFiveday);

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				result, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	// Ticket Close Time PieChart End

	// Tickets close time bar chart start
	public static JFreeChart closeTimeBar()
	{
		int[] closeTimes = Database.getTicketCloseTime();

		int oneDay = 0;
		int twoToFiveDay = 0;
		int moreThanFiveday = 0;

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		for (int i = 0; i < closeTimes.length; i++)
		{
			int daysToClose = closeTimes[i];
			if (daysToClose < 2)
			{
				oneDay++;
			} else if (daysToClose > 1 && daysToClose < 6)
			{
				twoToFiveDay++;
			} else
			{
				moreThanFiveday++;
			}
		}

		result.setValue(oneDay, "Days To Close", "One Day");
		result.setValue(twoToFiveDay, "Days To Close", "Two To five Days");
		result.setValue(moreThanFiveday, "Days To Close", "More Than Five Days");

		JFreeChart chart = ChartFactory.createBarChart("Ticket Close Times",
				"", "Days To Close", result, PlotOrientation.VERTICAL, false,
				true, false);

		return chart;
	}

	// Tickets close time bar chart end

	// Tickets by category pie chart start
	public static JFreeChart ticketByCatPie()
	{
		String title = "Ticket By Category";
		String[] catIDs = Database.getCatIDs();

		DefaultPieDataset result = new DefaultPieDataset();

		for (int i = 0; i < catIDs.length; i++)
		{
			String key = Database.getCatName(catIDs[i]).trim();
			int numTickets = Database.getCatTickets(catIDs[i].trim());

			if (numTickets > 0)
			{
				result.setValue(key, numTickets);

			}
		}

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				result, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	// Tickets by category pie chart end

	// Tickets by category bar chart start
	public static JFreeChart ticketByCatBar()
	{
		String[] catIDs = Database.getCatIDs();

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		for (int i = 0; i < catIDs.length; i++)
		{
			String key = Database.getCatName(catIDs[i]).trim();
			int numTickets = Database.getCatTickets(catIDs[i].trim());

			if (numTickets > 0)
			{
				result.setValue(numTickets, "Category", key);

			}
		}

		JFreeChart chart = ChartFactory.createBarChart("Tickets By Category",
				"Category", "Number Of Tickets", result,
				PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}

	// Tickets by category bar chart ends

	// Tickets by priority pie chart start
	public static JFreeChart ticketByPriorityPie()
	{
		String title = "Ticket By Priority";
		int[] priority =
		{
				1, 2, 3, 4, 5
		};

		DefaultPieDataset result = new DefaultPieDataset();

		for (int i = 0; i < priority.length; i++)
		{
			String key = "Priority: " + priority[i];
			int numTickets = Database.getPriorityTickets(priority[i]);

			if (numTickets > 0)
			{
				result.setValue(key, numTickets);

			}
		}

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				result, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}

	// Tickets by priority pie chart end

	// Tickets by priority bar chart start
	public static JFreeChart ticketByPriorityBar()
	{
		int[] priority =
		{
				1, 2, 3, 4, 5
		};

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		for (int i = 0; i < priority.length; i++)
		{
			String key = "Priority: " + priority[i];
			int numTickets = Database.getPriorityTickets(priority[i]);

			if (numTickets > 0)
			{
				result.setValue(numTickets, "Priority", key);

			}
		}

		JFreeChart chart = ChartFactory.createBarChart("Tickets By Priority",
				"Priority", "Number Of Tickets", result,
				PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}
	// Tickets by priority bar chart end
}
