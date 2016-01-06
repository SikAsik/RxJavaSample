package rxjavasample;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rxjavasample.model.Student;

public class T4 {

	public static void main(String[] args) {
		T4 t4 = new T4();
		t4.testFilter();
		t4.testTake();
		t4.testDistinct();
		t4.testFirstAndLast();
		t4.testElementAt();
		t4.testSampleAndThrottleFirst();
	}

	private void testFilter() {
		/**
		 * filter() �������������ǹ۲��б�����в���Ҫ��ֵ ����õ��÷�֮һ�����ڹ����б��е� null����.
		 * toSortedList() �����۲��߷�������Ϣ��ϳ�һ�������б�ÿ����Ϣ�������ʵ��Comparable�ӿڡ�
		 * 
		 */
		Observable.from(DataFactory.getStudents()).filter(new Func1<Student, Boolean>() {
			@Override
			public Boolean call(Student t) {
				return t.getAge() > 20;
			}
		}).toSortedList().subscribe(new Observer<List<Student>>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(List<Student> t) {
				System.out.println(t);
			}
		});
	}

	private void testTake() {
		System.out.println();
		/** take()��������ȡ�б���ǰn������ */
		Observable.from(DataFactory.getStudents()).take(2).subscribe(new Observer<Student>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Student t) {
				System.out.println(t);
			}
		});

		/** ���´��룬��take()����toSortedList()֮�󲻻����κ�Ч�� */
		Observable.from(DataFactory.getStudents()).toSortedList().take(2).subscribe(new Observer<List<Student>>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(List<Student> t) {
				System.out.println(t);
			}
		});

		/** takeLast()��������ȡ�б��к�n������ */
		Observable.from(DataFactory.getStudents()).takeLast(2).subscribe(new Observer<Student>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Student t) {
				System.out.println(t);
			}
		});
	}

	private void testDistinct() {
		System.out.println();
		/**
		 * distinct()��������ȥ�ظ�, ����ͬʱ��д�����equals�� hashcode����,�ж��Ƿ�ͬһ����ע������������������д
		 */

		/** ȡlist��ǰ3�������ظ����� */
		Observable<Student> observable = Observable.from(DataFactory.getStudents()).take(3).repeat(3);
		observable.distinct().subscribe(new Observer<Student>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Student t) {
				System.out.println(t);
			}
		});
	}

	private void testFirstAndLast() {
		System.out.println();
		/**
		 * ��ĳЩʵ���У�Firstû��ʵ��Ϊһ������Observable�Ĺ��˲�������
		 * ����ʵ��Ϊһ���ڵ�ʱ�ͷ���ԭʼObservableָ�����������������������Щʵ���У��������Ҫ����һ�����˲����������ʹ��Take(1)
		 * ����ElementAt
		 */
		Observable.just(1, 2, 3).first().subscribe(new Observer<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("first onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Integer t) {
				System.out.println(t);
			}

		});

		Observable.just(1, 2, 3).last().subscribe(new Observer<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("last onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Integer t) {
				System.out.println(t);
			}

		});
	}

	private void testElementAt() {
		System.out.println();
		/** ElementAt��������ȡԭʼObservable�������������ָ������λ�õ������Ȼ�����Լ���Ψһ���ݷ��� */
		Observable.just(1, 2, 3).elementAt(2).subscribe(new Observer<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("elementAt onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Integer t) {
				System.out.println(t);
			}

		});

		Student defStu = new Student();
		defStu.setName("default-name");

		Observable.from(DataFactory.getStudents()).elementAtOrDefault(5, defStu).subscribe(new Observer<Student>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Student t) {
				System.out.println(t);
			}
		});
	}

	private void testSampleAndThrottleFirst() {
		/**
		 * Sample��������ʱ�鿴һ��Observable��Ȼ�������ϴβ��������������������ݡ�
		 * ���������������ʱ�����һ��Ԫ�ض����������һ��Ԫ�أ����ǿ���ʹ�� throttleFirst(),�Ƿ�������android �û����ٵ����ť�ȵĴ����Է�ֹ�򿪶��ҳ��?����
		 * throttleFirst��throttleLast/sample��ͬ����ÿ�����������ڣ������Ƿ���ԭʼObservable�ĵ�һ�����ݣ������������һ�
		 */
		Observable.interval(1, TimeUnit.SECONDS).sample(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Long t) {
				System.out.println("sample" + t);
			}
			
		});
		
		Observable.interval(1, TimeUnit.SECONDS).throttleFirst(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Long t) {
				System.out.println("throttleFirst" + t);
			}
			
		});
		
		
		try {
			Thread.sleep(1000 * 60);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
