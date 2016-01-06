package rxjavasample;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rxjavasample.model.Student;

public class T5 {
	
	public static void main(String[] args) {
		T5 t5 = new T5();
		t5.testMap();
		t5.testFlatMap();
		t5.testScan();
	}
	
	private void testMap() {
		/**��Observable�����ÿһ������Ӧ��һ��������ִ�б任����*/
		Observable.from(DataFactory.getStudents()).map(new Func1<Student, Student>() {
			@Override
			public Student call(Student t) {
				t.setName(t.getName().toLowerCase());
				return t;
			}
		}).subscribe(new Action1<Student>() {
			@Override
			public void call(Student t) {
				System.out.println(t.getName());
			}
		});
	}
	
	private void testFlatMap() {
		System.out.println("=========testFlatMap �汾1===============");
		Observable<List<Student>> listObservable = Observable.from(DataFactory.getStudents()).toSortedList(); //ģ������,��������ȡ�������ݾ�����˵�
		
		//�汾1
		listObservable.subscribe(new Action1<List<Student>>() {
			@Override
			public void call(List<Student> list) {
				for (Student stu : list) {
					System.out.println(stu);
				}
			}
		});
		
		System.out.println("=========testFlatMap �汾2===============");
		listObservable.subscribe(new Action1<List<Student>>() {
			@Override
			public void call(List<Student> list) {
				Observable.from(list).subscribe(new Action1<Student>() {
					@Override
					public void call(Student t) {
						System.out.println(t);
					}
				});
			}
		});
		
		System.out.println("=========testFlatMap �汾3===============");
		listObservable.flatMap(new Func1<List<Student>, Observable<Student>>() {
			@Override
			public Observable<Student> call(List<Student> list) {
				return Observable.from(list);
			}
		}).subscribe(new Action1<Student>() {
			@Override
			public void call(Student t) {
				System.out.println(t);
			}
		});
	}
	
	private void testScan() {
		/**Scan��������ԭʼObservable����ĵ�һ������Ӧ��һ��������Ȼ���Ǹ������Ľ����Ϊ�Լ��ĵ�һ�����ݷ��䡣���������Ľ��ͬ�ڶ�������һ����������������������Լ��ĵڶ������ݡ������������������������ʣ����������С������������ĳЩ����±�����accumulator��*/
		
		Observable.from(DataFactory.getItems()).scan(new Func2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer t1, Integer t2) {
				return t1 + t2;
			}
		}).subscribe(new Action1<Integer>() {
			@Override
			public void call(Integer t) {
				System.out.print(t + "\t");
			}
		});
		System.out.println();
		
		Observable.from(DataFactory.getItems()).scan(1, new Func2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer t1, Integer t2) {
				return t1 + t2;
			}
		}).subscribe(new Action1<Integer>() {
			@Override
			public void call(Integer t) {
				System.out.print(t + "\t");
			}
		});
	}
}
