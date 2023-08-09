package br.com.souojor.examples.threads.status;

public class ThreadStatusGenerator {

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread 1 work.");
			}
		}, "thread 1");
		System.out.println(t.getState());

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread 2 work.");
			}
		}, "thread 2");
		t2.start();
		System.out.println(t2.getState());
	}

	class SynchronizedInfiniteRunnable implements Runnable {
	    @Override
	    public void run() {
	        commonResource();
	    }
	    
	    public static synchronized void infiniteWork() {
	    	int i = 0;
	        while(true) {
	        	i++;
	        }
	    }
	}
}
