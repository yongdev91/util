class StringUtil {

  /**
  * 이메일 주소 마스킹 처리
  * @param email
  * @return maskedEmailAddress
  */
  public static String getMaskedEmail(String email) {
    /*
    * 요구되는 메일 포맷
    * {userId}@domain.com
    * */
    String regex = "\\b(\\S+)+@(\\S+.\\S+)";
    Matcher matcher = Pattern.compile(regex).matcher(email);
    if (matcher.find()) {
        String id = matcher.group(1); // 마스킹 처리할 부분인 userId
        /*
        * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고,
        * 세글자인 경우 뒤 두글자만 마스킹,
        * 세글자 미만인 경우 모두 마스킹 처리
        */
        int length = id.length();
        if (length < 3) {
            char[] c = new char[length];
            Arrays.fill(c, '*');
            return email.replace(id, String.valueOf(c));
        } else if (length == 3) {
            return email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
        } else {
            return email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
        }
    }

    return email;
  }

  /**
  * 랜덤 패스워드 생성
  */
  public static String getRandomPasswd() {
    	 char [] pwCollectionSpCha = new char[] {
    			 'A','B','C','D','E','F','G','H','I','J','K','L','M'
                 ,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
                 ,'a','b','c','d','e','f','g','h','i','j','k','l','m'
                 ,'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    	 char [] charsetNumber = new char[] {
    			 '0','1','2','3','4','5','6','7','8','9'};

    	 char [] charsetSpecial = new char[] {
    			 '!', '@', '#', '$', '%', '^', '&', '+', '='};

    	 return getRandPw(1, charsetNumber) + getRandPw(8, pwCollectionSpCha) + getRandPw(1, charsetSpecial);
    }

    public static String getRandPw(int size, char[] pwCollection){
        String ranPw = "";
        for (int i = 0; i < size; i++) {
            int selectRandomPw = (int) (Math.random() * (pwCollection.length));
            ranPw += pwCollection[selectRandomPw];
        }
        return ranPw;
    }

    /**
	 * 모든 html 태그를 치환 (정규식)
	 * @param html string
	 * @return
	 * @throws Exception
	 */
	public static String replaceTag(String html, String replaceText) throws Exception {
		return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", replaceText);
	}

    // [박형준] 현재 Class와 MethodName
    public static String getClassMethodName( StackTraceElement[] stacks, String addStr ){
    	StringBuffer sb = new StringBuffer("");
    	if( stacks != null ){
    		StackTraceElement currentStack = stacks[0];
    		sb.append("Class - " + currentStack.getClassName());
    		sb.append(", ");
    		sb.append("Method - " + currentStack.getMethodName());

    	}
    	if( !"".equals(StringUtil.nvl(addStr,"")) ){
    		sb.append(", ");
    		sb.append(addStr);
    	}

    	return sb.toString();
    }

    /**
	 * 모든 html 태그를 제거 (정규식)
	 * @param html string
	 * @return
	 * @throws Exception
	 */
	public static String removeTag(String html) throws Exception {
		return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

  /**
	 * 문자열을 잘라 List로 리턴
	 * @param strValue : 문자열
	 * @param regex : 구분자
	 * @return ArrayList<String>
	 */
	public static List<String> splitStrList (String strValue, String regex) {
		List<String> resultList = new ArrayList<String>();

		String str = strValue.trim();
		try {
			if(isNotEmpty(str)) {
				String [] tmpStrList = str.split(regex);
				for(String sValue : tmpStrList) {
					resultList.add(sValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}

		return resultList;
	}

  /**
  * String null check
  */
  public static boolean isNull(String str) {
    if (str != null) {
      str = str.trim();
    }

    return (str == null || "".equals(str));
  }

  /**
  * null일때 대체값
  */
  public static String nvl(String str, String ifNull){
    return null2string(str, ifNull);
  }
}
