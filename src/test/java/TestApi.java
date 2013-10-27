import com.linkedin.hack.controller.RecommendController;


public class TestApi
{

  public TestApi()
  {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    RecommendController sc = new RecommendController();
    boolean calculate = sc.calculate("I7Im5VzJrW", "3ftC2HhF02", "aNgM20jA0k","64748", "Please consider this recommendation", true);

  }

}
