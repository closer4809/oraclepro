package com.javaex.phone;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;




public class PhoneApp {

	public static void main(String[] args) throws IOException{

	    
		
		System.out.println("**************************************");
		System.out.println("*          전화번호 관리 프로그램          *");
		System.out.println("**************************************");
		System.out.println("");
		
		
		Scanner sc = new Scanner(System.in);
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> phoneList; 
		
		
    	
		
    	
		boolean flag =true;	// while 문의 탈출조건 생
    	
    	while(flag) {
    		System.out.println(" 1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료 ");
    		System.out.println("--------------------------------------");
    		System.out.print(">메뉴번호: ");	// 계속 반복될 것
			
    		
				
    		int select = sc.nextInt();
			switch (select) { // switch문을 활용하여 1:1 대입되는 시스템 만들것
				
			
			
			case 1:{	// 1을 입력하였을
				
				phoneList = phoneDao.getPhoneList();
				//리스트를 for문으로 출력 --> 메소드로 정의
		
		
				printList(phoneList);
		
				
				
				System.out.println("");
				break;
			}	
			
			case 2:{ 
				System.out.println("2.등록");
				System.out.print(">이름: ");
				String name1 = sc.next();
				System.out.print(">휴대전화: ");
				String hp1 = sc.next();
				System.out.print(">회사전화: ");
				String company1 = sc.next();
				
				PersonVo iPhoneVO = new PersonVo(name1, hp1, company1);
				
				
				int iCount = phoneDao.phoneInsert(iPhoneVO);
				if(iCount>0) {
					System.out.println("[등록되었습니다.]");
				}else {
					System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
				}
				
				
		
				
				
			
				break;
			
			}
			
			case 3:{
				System.out.println("<3.수정>");
				System.out.print(">번호 : ");
				int name12 = sc.nextInt();
				System.out.print(">이름: ");
				String name1 = sc.next();
				System.out.print(">휴대전화: ");
				String hp1 = sc.next();
				System.out.print(">회사전화: ");
				String company1 = sc.next();
				
				PersonVo uPhoneVo = new PersonVo(name1, hp1, company1, name12);
				int uCount = phoneDao.phoneUpdate(uPhoneVo);
				
				
				
				break;
			}
			
			case 4:{
				System.out.println("<4.삭제>");
				System.out.print(">번호 : ");
				int num1 = sc.nextInt();
				//phoneList.remove(num1-1);
				int dCount = phoneDao.phoneDelete(num1);
				
				
				
				
				
				
				//리스출력
				//DB에서 가져오기
				phoneList = phoneDao.getPhoneList();
				//리스트를 for문으로 출력 --> 메소드로 정의
				printList(phoneList);
				
				
				break;
			}
			
			case 5:{
				System.out.println("검색어를 입력해주세요.");
				System.out.print("검색어 : ");
				String search = sc.nextLine();
				

				List<PersonVo> searchList = phoneDao.getPersonList(search);
				printList(searchList);
				
				break;
			}
			
			case 6:{
				
				System.out.println("**************************************");
				System.out.println("*               감사합니다              *");
				System.out.println("**************************************");
				System.out.println("");
				
				
				flag=false;
				break;
			}
			
			default:{
				System.out.println("[다시 입력해주세요.]");
				break;
				
				
			}
			
			
		
			}}
		
			
			
			
			}
			
			
		
			public static void printList(List<PersonVo> phoneList) {
    		
    		for(int i=0; i<phoneList.size(); i++) {
    			
    			
    			PersonVo personVo = phoneList.get(i);
    			System.out.println(personVo.getPhoneId()+"\t" +personVo.getName() + "\t" + personVo.getHp() + "\t" + personVo.getCompany());
    		
    		
    		
    		}
    	}
	

}
		
		
		

	



	


