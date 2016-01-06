package rxjavasample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func2;

public class T6 {
	public static void main(String[] args) {
		T6 t6 = new T6();
		t6.testMerge();
		t6.testConcat();
		t6.testZip();
		t6.testCombineLatest();
	}

	private void testMerge() {
		/**
		 * ʹ��Merge����������Խ����Observables������ϲ����ͺ���������һ��������Observableһ����
		 * Merge���ܻ��úϲ���Observables��������ݽ���
		 * ����һ�����ƵĲ�����Concat���������ݽ������ᰴ˳��һ������һ��������Observables�ķ������
		 * ע�����ʱ��toast��Ϣ���������Ϊÿ��Observable�׳��Ĵ��󽫻��Ϻϲ���
		 */
		Observable<Integer> listObservable = Observable.from(DataFactory
				.getItems());
		List<Integer> reverseList = new ArrayList<Integer>();
		reverseList.addAll(DataFactory.getItems());
		Collections.reverse(reverseList);
		Observable<Integer> reverseListObservable = Observable
				.from(reverseList);
		Observable.merge(listObservable, reverseListObservable).subscribe(
				new Subscriber<Integer>() {
					@Override
					public void onNext(Integer i) {
						System.out.print(i + "\t");
					}

					@Override
					public void onError(Throwable e) {
						System.err.println(e.getMessage());
					}

					@Override
					public void onCompleted() {
						System.out.println("onCompleted");
					}
				});
	}

	private void testConcat() {
		Observable<Integer> listObservable = Observable.from(DataFactory
				.getItems());
		List<Integer> reverseList = new ArrayList<Integer>();
		reverseList.addAll(DataFactory.getItems());
		Collections.reverse(reverseList);
		Observable<Integer> reverseListObservable = Observable
				.from(reverseList);
		Observable.concat(listObservable, reverseListObservable).subscribe(
				new Subscriber<Integer>() {
					@Override
					public void onNext(Integer i) {
						System.out.print(i + "\t");
					}

					@Override
					public void onError(Throwable e) {
						System.err.println(e.getMessage());
					}

					@Override
					public void onCompleted() {
						System.out.println("onCompleted");
					}
				});
	}

	private void testZip() {
		/**
		 * ͨ��һ�����������Observables�ķ������ϵ�һ�𣬻�����������Ľ��Ϊÿ������巢�䵥�������
		 * Zip����������һ��Obversable ��
		 * ��ʹ�����������˳������������Observables����������Ȼ������������������صĽ��
		 * ���������ϸ��˳��Ӧ�������������ֻ�����뷢�����������ٵ��Ǹ�Observableһ��������ݡ� 
		 * Javadoc: zip(Iterable,FuncN)) 
		 * Javadoc: zip(Observable,FuncN)) 
		 * Javadoc: zip(Observable,Observable,Func2)) (�������оŸ�Observables����)
		 */
		Observable<String> observable1 = Observable.just("A", "B", "F", "G","N");
		Observable<String> observable2 = Observable.just("C", "F", "R");
		Observable.zip(observable1, observable2,
				new Func2<String, String, String>() {

					@Override
					public String call(String t1, String t2) {
						return t1 + t2;
					}

				}).subscribe(new Observer<String>() {

			@Override
			public void onNext(String str) {
				System.out.print(str + "\t");
			}

			@Override
			public void onError(Throwable e) {
				System.err.println(e.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

		});

	}
	
	private void testCombineLatest() {
		/**zip() ���������δ���������Observables���෴�� combineLatest() �������������������
		 * ��� Observable1 ������A���� Observable2 ������B��C�� combineLatest() ������鴦��AB��AC
		 */
		Observable<String> observable1 = Observable.just("A", "B", "F", "G","N");
		Observable<String> observable2 = Observable.just("C", "F", "R");
		Observable.combineLatest(observable1, observable2,
				new Func2<String, String, String>() {

					@Override
					public String call(String t1, String t2) {
						return t1 + t2;
					}

				}).subscribe(new Observer<String>() {

			@Override
			public void onNext(String str) {
				System.out.print(str + "\t");
			}

			@Override
			public void onError(Throwable e) {
				System.err.println(e.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}

		});
	}
}
