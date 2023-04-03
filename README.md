Kotlin Jetpack Compose 활용해서 Android App 만드는 팀입니다.

# 코딩 컨벤션

- 네이밍 컨벤션
   
   변수, 함수 : camelCase 사용.    ex) var myAge = 24
   
   상수 : uppercased underscore-separated 사용.    ex) const val MY_AGE = 24
   
   이미지, font 등 파일 : snake_case 사용.    ex) suite_bold.ttf
- 그 외 : 코틀린 공식 컨벤션

참고 : https://kotlinlang.org/docs/coding-conventions.html#trailing-commas


# commit message 양식

기본적으로 Issues에 있는 Label의 양식을 따른다.
| Label             | 내용                                                                         |
| ----------------- | --------------------------------------------------------------------------- |
| FEAT              |  새로운 기능 추가(개발)                                                          |
| DOCS              |  문서 수정                                                                    |
| FIX               |  버그 수정                                                                     |
| REFACTOR          |  코드 리펙토링                                                                  |
| STYLE             |  코딩스타일 변경(코드 자체에는 변경 없음)                                             |

위와 같은 Label을 맨 앞에 적고 : 으로 구분 후 Issues에 있는 작업내용을 제목으로 작성  
세부사항은 아래 description에 적기  
기능을 여러개 구현했을 경우 상세 내용에서 문단 구분하여 작성  

EX)
| **FEAT : Login Page, Basic Page Format 구현**         |
| ---------------------------------------------------- |
| Splash Screen 후 Login Page로 넘어가도록 구현<br/>버튼을 누르면 메인페이지로 넘어가도록 설정<br/>OAuth와 같은 기능적인 부분은 추후 구현 예정<br/> <br/> Basic Page Format 완성 <br/>하단바에 4개의 아이콘 사용. 메인, 마이페이지, 커뮤니티, 설정 구현 <br/>임시 포인트 컬러 파란색으로 구현 <br/>각 페이지에 있을 경우 해당 아이콘 포인트 컬러로 나타냄<br/> OnceScreen을 통해 재사용성 증가  |

# 협업 방법  
main : 버그없는 완성본이 올라가는 branch  
develop : 실질적인 개발branch  
<br/>
develop branch를 기반으로 새 branch를 만들어서 작업.  
새 branch의 이름은 개발하고자 하는 기능의 이름  
관련 작업이 끝난 후 오류여부 확인 후 develop branch에 병합.  
develop branch에서 모든 기능의 연동성 확인 후 main으로 병합  


<br/>
<br/>
Readme 꾸며줘요 캡틴 킴~
