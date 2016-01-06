package rxjavasample;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class T1 {

	public static void main(String[] args) {
		T1 t1 = new T1();
		t1.testCreate();
		t1.testFrom();
		t1.testJust();
	}
	
	private void testCreate() {
		System.out.println("====================Observable.create========================");
		Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				for (int i = 0; i < 10; i++) {
					subscriber.onNext("value=" + i);
				}
				subscriber.onCompleted();
			}
		}).subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.println(t);
			}
			
		});
		
		Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				for (int i = 0; i < 10; i++) {
					t.onNext("value=" + i);
				}
				t.onCompleted();
			}
		}).subscribe(new Observer<String>() {
			@Override
			public void onNext(String t) {
				System.out.println(t);
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}
			
		});
	}
	
	private void testFrom() {
		System.out.println("====================Observable.from========================");
		/**from()���������Դ�һ���б�/����������Observable,��һ����һ���Ĵ��б������з������ÿһ������*/
		
		
		
		Observable.from(DataFactory.getItems()).subscribe(new Observer<Integer>() {

			@Override
			public void onNext(Integer t) {
				System.out.println(t);
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}
			
		});
	}
	
	private void testJust() {
		System.out.println("====================Observable.just========================");
		/**���Ǵ�����Observable���� just()ִ�к���helloMicky()��һ�����Ƕ���Observableʱ�����ͻᷢ���helloMicky()���ص�ֵ��*/
		
		Observable.just(helloMicky()).subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.print(t);
			}
		});
		System.out.println();
		//����������������˼��������Android������ʣ�rxjava�ܲ���ֱ�Ӱ�helloMicky()���ڷ����̷߳��ʣ�����subscribe�������̷߳����أ������ͽ����android������callback������,���������������...��
		
		Observable.just("A", "B", "C", "D").subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.print(t);
			}
		});
		System.out.println();
		/**just() ����Ҳ���Խ����б�����飬���� from() ��������������������б���ÿ��ֵ,�����ᷢ�������б�*/
		Observable.just(DataFactory.getItems()).subscribe(new Action1<List<Integer>>() {
			@Override
			public void call(List<Integer> t) {
				for(int i = 0; i < t.size(); i++) {
					System.out.print(t.get(i));
				}
			}
		});
		System.out.println();
	}
	
	private String helloMicky() {
		return "Hello Micky";
	}

	
	
	/**��������Ҫ��һ��Observable���ϲ��ٷ���������������ʱ�����ǿ���ʹ�� empty() �����ǿ���ʹ�� never() ����һ�����������ݲ���Ҳ��Զ���������Observable������Ҳ����ʹ�� throw() ����һ�����������ݲ����Դ��������Observable��*/
}
