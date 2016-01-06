package rxjavasample;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;

public class T3 {

	public static void main(String[] args) {
		T3 t3 = new T3();
		t3.testRepeat();
		t3.testDefer();
		t3.testTimer();
		t3.testInterval();
		t3.testRange();
	}

	private void testRepeat() {
		/** �ظ�ִ�����μ���ӡABC���� */
		Observable.just("A", "B", "C").repeat(3).subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.print(t + "\t");
			}
		});
		System.out.println();
	}

	private int i = 0;

	private void testDefer() {
		/**
		 * just���������ڴ���Observable�ͽ����˸�ֵ��������defer���ڶ����߶���ʱ�Ŵ���Observable��
		 * ��ʱ�Ž��������ĸ�ֵ����
		 */
		i = 11;
		Observable<Integer> justObservable = Observable.just(i);
		Observable<Integer> deferObservable = Observable.defer(new Func0<Observable<Integer>>() {

			@Override
			public Observable<Integer> call() {
				return Observable.just(i);
			}
		});

		i = 15;

		justObservable.subscribe(new Subscriber<Integer>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Integer i) {
				System.out.println("just result:" + i);
			}
		});

		deferObservable.subscribe(new Subscriber<Integer>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Integer i) {
				System.out.println("defer result:" + i);
			}
		});
	}
	
	private void testTimer() {
		/**Ĭ���������������һ�����߳��ϵ�;    �ӳ�����*/
		Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

			@Override
			public void onCompleted() {
				System.out.println("timer onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Long t) {
				System.out.println(t);
			}
			
		});
        try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private void testInterval() {
		/**Ĭ���������������һ�����߳��ϵ� ;   ÿ���������һ������ */
        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
 
            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }
 
            @Override
            public void onNext(Long t) {
                System.out.println("Next:" + t);
            }
        });
        try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private void testRange() {
		/**����һ���ڴ�n��ʼ������Ϊm����������*/
		System.out.println("===================range");
		Observable.range(3, 10).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
 
            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }
 
            @Override
            public void onNext(Integer i) {
                System.out.println("Next:" + i.toString());
            }
        });
		System.out.println("===================range");
		Observable.range(3, 10).toSortedList().subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
 
            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }
 
            @Override
            public void onNext(List<Integer> i) {
                System.out.println("Next:" + i.toString());
            }
        });
	}

}
