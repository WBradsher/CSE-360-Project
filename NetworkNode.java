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
	public void setDuration(int dur1)
	{
		Duration = dur1;
	}
	public void setDependencies(String depend1)
	{
		Dependencies = depend1; 
	}
}
