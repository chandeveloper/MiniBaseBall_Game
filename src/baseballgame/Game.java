package baseballgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	// main메서드 생성 throws IOException으로 예외처리
	public static void main(String[] args) throws IOException {
		//	BufferedReader 입력받기 위한 준비물
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 홈팀 과 어웨이팀의 객체 생성
		HomeTeam homeTeam = new HomeTeam();
		AwayTeam awayTeam = new AwayTeam();

		while (true) {
			// 게임 시작
			System.out.println("☆★☆★ 미니 야구 게임 ☆★☆★");
			System.out.println("1. 게임시작");
			System.out.println("2. 게임종료");
			System.out.print("3. 입력 >> ");
			// int값을 입력받아 게임을 시작/종료 한다.
			int start = Integer.parseInt(br.readLine());
			System.out.println();	// 한줄 띄어쓰기

			if (start == 1) {
				// 팀 이름 설정 
				System.out.println("※※ 팀 이름 설정 ※※");
				System.out.println();
				System.out.println("홈팀 이름설정");
				System.out.print("입력 >> ");
				// 값을 입력받아 homeTim객체의 tim1변수에 저장
				homeTeam.hTeam = br.readLine();
				System.out.println();
				System.out.println("어웨이팀  이름설정");
				System.out.print("입력 >> ");
				// 값을 입력받아 awayTim객체의 tim2변수에 저장
				awayTeam.aTeam = br.readLine();
				System.out.println();
				
				// 경기이닝 수 입력
				System.out.print("이닝 수 입력 >> ");
				int n = Integer.parseInt(br.readLine());
				System.out.println();	// 한줄 띄어쓰기
				
				// n이닝만큼 경기를 진행
				for (int i = 1; i <= n; i++) {
					System.out.println("※※※※※ " + i + "회초 ※※※※※");
					awayTeam.a_hit();		// away팀의 i회초 공격 메서드
					for (int j = 0; j < 1; j++) {
						System.out.println("◈◈◈ 3아웃 공수교대 ◈◈◈");
						System.out.println();	// 한줄 띄어쓰기
						System.out.println("※※※※※ " + i + "회말 ※※※※※");
						homeTeam.h_hit();	// home팀의 i회말 공격 메서드
					}
						System.out.println("◈◈◈ 3아웃 공수교대 ◈◈◈");
				}

				
			} else if (start == 2) {
				// 게임종료를 선택했을때
				System.out.println("게임을 종료합니다");	
				break;
			} else {
				// 게임시작, 게임종료가 아닌걸 눌렀을때
				System.out.println("잘못눌렀습니다");
				System.out.println("다시 입력하세요");
				System.out.println();	// 한줄 띄어쓰기
				continue;
			}
			
			// 게임결과 메소드
			System.out.println("※※※※※ 경기가 종료되었습니다 ※※※※※");
			if (homeTeam.hScore > awayTeam.aScore) {			// HomeTim이 이겼을때
				System.out.println("양 팀 치열한 승부끝에");
				System.out.println(homeTeam.hTeam + "가(이) 승리하였습니다");
				System.out.println("◈◈ 최종스코어 ◈◈");
				System.out.println(homeTeam.hTeam + " : " + homeTeam.hScore);
				System.out.println(awayTeam.aTeam + " : " + awayTeam.aScore);
			} else if (homeTeam.hScore < awayTeam.aScore) {	// AwayTim이 이겼을때
				System.out.println("양 팀 치열한 승부끝에");
				System.out.println(awayTeam.aTeam + "가(이) 승리하였습니다");
				System.out.println("◈◈ 최종스코어 ◈◈");
				System.out.println(homeTeam.hTeam + " : " + homeTeam.hScore);
				System.out.println(awayTeam.aTeam + " : " + awayTeam.aScore);
			} else {
				System.out.println("양 팀 치열한 승부끝에 ");	// 무승부일때
				System.out.println("결과는 무승부입니다.");
				System.out.println("◈◈ 최종스코어 ◈◈");
				System.out.println(homeTeam.hTeam + " : " + homeTeam.hScore);
				System.out.println(awayTeam.aTeam + " : " + awayTeam.aScore);
			}
			break;
		}	// while문 끝
	
	}
}