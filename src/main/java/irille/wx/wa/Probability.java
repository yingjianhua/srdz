package irille.wx.wa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/** 根据概率获取奖品的测试类 */
public class Probability {
	public static void main(String[] args) {
		Random r = new Random();
		
		for(int i=0;i<10;i++) {
			System.out.println(r.nextDouble()*100);
		}
    
  }

  /** List 获取 */
  public static int getGumByList(Double rate, List<WaActSet> actSets) {
    double random = new Random().nextDouble()*100;
    int prizeRate =0;// 中奖率 
    Iterator<Gem> it = initGems(actSets,rate).iterator();
    while (it.hasNext()) {
      Gem gem = it.next();
      prizeRate += gem.getPriority();
      if (random < prizeRate) {
        return gem.getItem();
      }
    }
    return 0;
  }

  /** 初始化list */
  static List<Gem> initGems(List<WaActSet> actSets,double gl) {
    List<Gem> gums = new ArrayList<Gem>();
    for(int i=1;i<=actSets.size();i++) {
    	gums.add(new Gem(i, gl/3));
    }
    return gums;
  }

  /**奖品类*/
  static class Gem {
    /** 奖品名称 */
    private int item;
    /** 奖品概率 */
    private double priority;

    public Gem() {
      super();
    }

    public Gem(int item, double priority) {
      super();
      this.item = item;
      this.priority = priority;
    }

    @Override
    public String toString() {
      return "Gum [name=" + item + ", priority=" + priority + "]";
    }

   

    public double getPriority() {
      return priority;
    }

    public void setPriority(double priority) {
      this.priority = priority;
    }

    public int getItem() {
      return item;
    }

    public void setItem(int item) {
      this.item = item;
    }

  }
}
