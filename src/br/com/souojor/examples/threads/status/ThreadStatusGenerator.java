package br.com.souojor.examples.threads.status;

public class ThreadStatusGenerator {
	static Thread t5;

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread 1 work done.");
			}
		}, "thread 1");
		System.out.println(t1.getName() + ": " + t1.getState());

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread 2 work done.");
			}
		}, "thread 2");
		t2.start();
		System.out.println(t2.getName() + ": " + t2.getState());
		
		Thread t3 = new Thread(new SynchronizedInfiniteRunnable(), "thread 3");
        Thread t4 = new Thread(new SynchronizedInfiniteRunnable(), "thread 4");
        
        t3.start();
        t4.start();
        
		Thread.sleep(1000);
        
		System.out.println(t3.getName() + ": " + t3.getState());
        System.out.println(t4.getName() + ": " + t4.getState());
        
        t5 = new Thread(new Runnable() {

			@Override
			public void run() {
				Thread t6 = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
				            Thread.sleep(1000);
				        } catch (InterruptedException e) {
				            Thread.currentThread().interrupt();
				            e.printStackTrace();
				        }
						System.out.println(t5.getName() + ": " + t5.getState());
						System.out.println("thread 6 work done.");
					}
				}, "thread 6");
				
				t6.start();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
					e1.printStackTrace();
				}
				System.out.println(t6.getName() + ": " + t6.getState());
				try {
					t6.join();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}

				System.out.println("thread 5 work done.");
			}
		}, "thread 5");

        t5.start();
        
        t5.join();
        
        System.exit(0);
	}

	static class SynchronizedInfiniteRunnable implements Runnable {
	    @Override
	    public void run() {
	    	infiniteWork();
	    }
	    
	    public static synchronized void infiniteWork() {
	    	int i = 0;
	        while(true) {
	        	i++;
	        }
	    }
	}
}
