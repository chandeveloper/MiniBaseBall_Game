package baseballgame;

import java.util.Random;
import java.util.Scanner;

public class AwayTeam {

	static String aTeam;				// 어웨이팀 이름을 입력받아 저장할 static변수
	static int aScore;					// 어웨이팀 점수를 저장할 static변수

	public void a_hit() {
		Random r = new Random();		// 랜덤클래스를 이용해 변수 r 생성
		Scanner sc = new Scanner(System.in);

		int percent = 0;				// 타격했을때 결과값의 확률을 부여할 변수
		int speed = 0;					// 구속을 담을 변수
		int outCnt = 0;					// 아웃카운트를 담을 변수

		boolean[] base;					// boolean타입 base라는 배열선언 해서
		base = new boolean[4];			// 홈(0번방), 1(1번방), 2(2번방), 3(3번방), 루의 값을 넣어준다
		
		// outCnt가 2이하일때까지 공격을 반복한다
		while (outCnt <= 2) {
			System.out.println("◆◆◆ " + aTeam + " 공격 ◆◆◆");
			
			// 타격전 주자가 몇루에 있는지를 출력할 문장
			/*
				만루일때 		: base배열의 1, 2, 3번방이 전부 true면 주자 만루 출력
			    만루가 아닐때  	: base i번방이 true이면 주자 i루 출력
			 */
			for (int i = 1; i <= 3; i++) {
				if (base[1] && base[2] && base[3]) {			
					System.out.println("주자 만루 풀베이스");
				} else if(base[i]){								
					System.out.println("주자 : " + i + "루");
				}
			}
			
			// 게임중 플레이화면
			System.out.println("아웃카운트 : " + outCnt + "아웃");
			System.out.println("1. 타격하기");
			System.out.println(HomeTeam.hTeam + " : " + HomeTeam.hScore);
			System.out.println(AwayTeam.aTeam + " : " + AwayTeam.aScore);
			System.out.print("입력 >> ");
			int hit = sc.nextInt();					
			System.out.println();	// 한줄 띄어쓰기
			
			// hit가 1이면 if문 실행
			if (hit == 1) {
				
				// 투수가 공을 던져서 타자한테 가기까지의 시간을 표현한 Thread.sleep문
				System.out.println("투수 와인드업!!");
				System.out.println();
				try {
					Thread.sleep(1700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				percent = r.nextInt(100) + 1;			// 타격했을때 나올 확률을 Random클래스로 구할 변수
				speed = r.nextInt(10) + 150;			// 구속을 Random클래스로 구할 변수
				
				// 1~40의 숫자가 나오면 아웃 / 40% 확률
				if (percent >= 1 && percent <= 40) {
					outCnt++;							// 아웃이면 outCnt가 1씩 증가
					System.out.println("타자 쳤습니다!!!");
					System.out.println("타격결과 : 타자 아웃!!");
					System.out.println("구속 : " + speed + "km");
					System.out.println("======================");
					System.out.println();
				}
				
				// 41~60의 숫자가 나오면 안타 / 20% 확률
				if (percent >= 41 && percent <= 60) {
					System.out.println("타자 쳤습니다!!!");
					System.out.println("★타격결과 : 안타★");
					System.out.println("구속 : " + speed + "km");
					System.out.println("======================");
					System.out.println();
					/*
					   안타일때 : for문으로 3번 반복 하는데 k는 3이고 3, 2, 1 역순으로 배열을 확인해준다.
					    		k == 3 		: base의 k번방(3루)이 true이면 주자는 홈인 
					              		      aScore++ 후 k번방(3루)은 false처리
					            k == 2, 1  	: base의 k번방(2루, 1루)이 true일 때는 false처리 후 
					              			  k번방(2루, 1루)에 1을 더해서 true를 준다. 
					            타자주자 		: 타자는 1루로 진루(1번방에 true)
					*/
					for (int k = 3; k >= 1; k--) {
						if (base[k]) {
							if (k == 3) { 				// 3루에 있는 선수는 홈인.
								aScore++; 				// 동시에 점수 획득.
								base[k] = false; 		// 3루는 비워진다.
								continue;
							}
							// 1, 2루인 경우 1루씩 진루하고 원래 있던 자리는 비워진다.
							base[k] = false;
							base[k + 1] = true;
						}
					}
					base[1] = true; // 타자는 1루로 진루.
				}
				
				// 61~77의 숫자가 나오면 2루타 / 17% 확률
				if (percent >= 61 && percent <= 77) {
					System.out.println("타자 쳤습니다!!!");
					System.out.println("★★타격결과 : 2루타★★");
					System.out.println("구속 : " + speed + "km");
					System.out.println("======================");
					System.out.println();
					/*
					   2루타일때 : for문으로 3번 반복 하는데 k는 3이고 3, 2, 1 역순으로 배열을 확인해준다.
					    		 k == 3, 2  : base의 k번방(3루, 2루)이 true이면 주자는 홈인 
					              		      aScore++ 후 k번방(3루, 2루)은 false처리
					             k == 1 	: base의 k번방(1루)이 true일 때는 false처리 후 
					              			  k번방(1루)에 2를 더해서 true를 준다. 
					             타자주자 	 	: 타자는 2루로 진루(2번방에 true)
					*/
					for (int k = 3; k >= 1; k--) {
						if (base[k]) {
							if (k == 3 || k == 2) {		 // 3루 혹은 2루에 있는 선수는 홈인.
								aScore++; 				 // 동시에 점수 획득.
								base[k] = false; 		 // 2루 또는 3루는 비워진다.
								continue;
							}
							// 1루인 경우 3루로 진루하고 1루는 비워진다.
							base[k] = false;
							base[k + 2] = true;
						}
					}
					base[2] = true; // 타자는 2루로 진루.
				}
				
				// 78~90의 숫자가 나오면 3루타 / 13% 확률
				if (percent >= 78 && percent <= 90) {
					System.out.println("타자 쳤습니다!!!");
					System.out.println("★★★타격결과 : 3루타★★★");
					System.out.println("구속 : " + speed + "km");
					System.out.println("======================");
					System.out.println();
					/*
					   3루타일때 : for문으로 3번 반복 하는데 k는 3이고 3, 2, 1 역순으로 배열을 확인해준다.
					    		 k == 3, 2, 1  	: base의 k번방(3루, 2루, 1루)이 true이면 주자는 홈인 
					              		          aScore++ 후 k번방(3루, 2루, 1루)은 false처리
					             타자주자 	 		: 타자는 3루로 진루(3번방에 true)
					*/
					for (int k = 3; k >= 1; k--) {
						if (base[k]) { 						// k루의 선수는 홈인.
							aScore++; 						// 동시에 점수 획득.
							base[k] = false;				// k루의 베이스는 비워진다
						}
					}
					base[3] = true; 						// 타자는 3루로 진루.
				}
				
				// 91~100의 숫자가 나오면 홈런 / 10% 확률
				if (percent >= 91 && percent <= 100) {
					
					// 홈런쳤을때 공이 날아가는 시간을 Thread.sleep으로 효과를 표현
					try {
						System.out.println("타자 쳤습니다!!!");
						System.out.println("쭉쭉 뻗어나가는 공");
						System.out.println("담장뒤로~~ 담장뒤로~~~");
						Thread.sleep(2300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("!!☆☆☆홈런☆☆☆!!");
					System.out.println("구속 : " + speed + "km");
					System.out.println("======================");
					System.out.println();
					/*
					   홈런일때 : for문으로 3번 반복 하는데 k는 1이고 1, 2, 3 순으로 배열을 확인해준다.
					    		k == 1, 2, 3  	: base의 k번방(1루, 2루, 3루)이 true이면 주자는 홈인 
					              		          aScore++ 후 k번방(1루, 2루, 3루)은 false처리
					            타자주자 	 		: aScore++ 홈인
					*/
					for (int k = 1; k <= 3; k++) {
						if (base[k]) { 						// 모든 주자가 홈인.
							aScore++; 						// 주자 1명 당 점수 1점씩 획득
							base[k] = false;
						}
					}
					aScore++; 								// 홈런친 타자의 점수 1점 추가.
				}
				
			} 
			// 타격하기가 아닌 다른걸 눌렀을때
			else {
				System.out.println("잘못눌렀습니다");
				System.out.println("다시 입력하세요");
				System.out.println();
				continue;
			}
		}	// while문 끝
	
	}
}