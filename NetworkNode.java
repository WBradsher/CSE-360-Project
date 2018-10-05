import java.util.StringTokenizer;

public class NetworkNode 
{
	private String ActivityName;
	private int Duration;
	private String Dependencies;
	public NetworkNode(String name, int dur, String depend)
	{
		ActivityName = name;
		Duration = dur;
		Dependencies = depend;
	}
	public String getName()
	{
		return ActivityName;
	}
	public int getDuration()
	{
		return Duration;
	}
	public String getDependencies()
	{
		return Dependencies;
	}
}
