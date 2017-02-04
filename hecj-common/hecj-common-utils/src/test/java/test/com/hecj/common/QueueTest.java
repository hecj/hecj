package test.com.hecj.common;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> queue=new LinkedList<String>();
		  queue.offer("1");
	        queue.offer("2");
	        queue.offer("3");
	        queue.offer("4");
	        queue.offer("5");
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	        System.out.println("队列中的元素是:"+queue);
	        //弹出元素
	        queue.poll();
	}

}
