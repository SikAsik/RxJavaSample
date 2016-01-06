package rxjavasample;

import rx.Observer;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class T2 {

	/**
	 * subject ��һ������Ķ�����������һ��ObservableͬʱҲ������һ��Observer������Ϊ���������������һ��������
	 * һ��������Զ���һ��Observable������һ���۲��ߣ����������Է����µ����ݣ����ߴ��������ܵ������ݣ�����һ��Observable�������ԣ�
	 * ��Ϊһ��Observable���۲����ǻ����������ⶼ���Զ�������
	 * ���л������� Subject ����һ�� Subscriber ʹ�ã�ע�ⲻҪ�Ӷ���߳��е�������onNext����������������onϵ�з�����������ܵ���ͬʱ����˳�򣩵��ã����Υ��ObservableЭ�飬��Subject�Ľ�������˲�ȷ���ԡ�Ҫ����������⣬����Խ� Subject ת��Ϊһ�� SerializedSubject ��������������
	 * mySafeSubject = new SerializedSubject( myUnsafeSubject );
	 */
	public static void main(String[] args) {
		T2 t2 = new T2();
		System.out.println("===================testPublishSubject==========================");
		t2.testPublishSubject();
		System.out.println("===================testBehaviorSubject==========================");
		t2.testBehaviorSubject();
		System.out.println("===================testReplaySubject==========================");
		t2.testReplaySubject();
		System.out.println("===================testAsyncSubject==========================");
		t2.testAsyncSubject();
		
	}

	private void testPublishSubject() {
		/** PublishSubject�Ĺ۲��߽��յ����Ǻ�������Ϣ*/
		Observer<String> observer1 = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

		};
		
		Observer<String> observer2 = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

		};
		PublishSubject<String> publishSubject = PublishSubject.create();
		publishSubject.subscribe(observer1);
		publishSubject.onNext("A");
		publishSubject.onNext("B");
		publishSubject.subscribe(observer2);
		publishSubject.onNext("C");
		publishSubject.onNext("D");
		publishSubject.onCompleted();
		System.out.println();
	}

	private void testBehaviorSubject() {
		/** BehaviorSubject�Ĺ۲��߽��յ�����Զ���������Ϣ �ͺ�������Ϣ*/
		Observer<String> observer = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

		};
		
		//�յ�������Ϣ
		BehaviorSubject<String> subject1 = BehaviorSubject.create("default");
		subject1.subscribe(observer);
		subject1.onNext("A");
		subject1.onNext("B");
		subject1.onNext("C");
		System.out.println();

		//�����յ�default��A
		BehaviorSubject<String> subject2 = BehaviorSubject.create("default");
		subject2.onNext("A");
		subject2.onNext("B");
		subject2.subscribe(observer);
		subject2.onNext("C");
		subject2.onNext("D");
		System.out.println();
		
		//ֻ���յ�onCompleted
		BehaviorSubject<String> subject3 = BehaviorSubject.create("default");
		subject3.onNext("A");
		subject3.onNext("B");
		subject3.onCompleted();
		subject3.subscribe(observer);
		System.out.println();

		// ֻ���յ�error
		BehaviorSubject<String> subject4 = BehaviorSubject.create("default");
		subject4.onNext("A");
		subject3.onNext("B");
		subject4.onError(new RuntimeException("error"));
		subject4.subscribe(observer);

		System.out.println();
	}

	private void testReplaySubject() {
		/**ReplaySubject�Ỻ��������Ϣ�����Թ۲��߶����յ�������Ϣ*/
		Observer<String> observer1 = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

		};
		
		Observer<String> observer2 = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

		};
		ReplaySubject<String> publishSubject = ReplaySubject.create();
		publishSubject.subscribe(observer1);
		publishSubject.onNext("A");
		publishSubject.onNext("B");
		publishSubject.subscribe(observer2);
		publishSubject.onNext("C");
		publishSubject.onNext("D");
		publishSubject.onCompleted();
		System.out.println();
	}
	
	private void testAsyncSubject() {
		/**��Observable���ʱAsyncSubjectֻ�ᷢ�����һ����Ϣ���Ѿ����ĵ�ÿһ���۲���,���û�е���onCompleted�򱻹۲��߲��ᷢ���κ���Ϣ���۲���*/
		Observer<String> observer = new Observer<String>() {

			@Override
			public void onNext(String t) {
				System.out.print(t + "\t");
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}
		};
		
		AsyncSubject<String> publishSubject1 = AsyncSubject.create();
		publishSubject1.subscribe(observer);
		publishSubject1.onNext("A");
		publishSubject1.onNext("B");
		publishSubject1.onNext("C");
		
		AsyncSubject<String> publishSubject2 = AsyncSubject.create();
		publishSubject2.subscribe(observer);
		publishSubject2.onNext("A");
		publishSubject2.onNext("B");
		publishSubject2.onNext("C");
		publishSubject2.onCompleted();
		System.out.println();
	}
}
