public class YoutubeUtil {
  /**
   * 유튜브 비디오 아이디 가져오기
   * @author yong
   * @date 2020.11.18
   * @param link
   * @return
   */
  public static String getYoutubeVideoId(String link) {
    String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
      Pattern compiledPattern = Pattern.compile(pattern);
      Matcher matcher = compiledPattern.matcher(link);
      if(matcher.find()){
          return matcher.group();
      } else {
        return "";
      }
  }

  /**
   * 유튜브 썸네일 이미지 가져오기
   * @author yong
   * @datte 2020.11.18
   * @param videoId
   * @param resolutionType(해상도) L:저품질, M:중간품질, H:고품질
   * @return
   */
  public static String getYoutubeThumImg(String videoId, String resolutionType) {
    String imgYoutubeUrl = "https://img.youtube.com";
    String path = "/vi";
    String resolutionPath = "0.jpg";

    if("L".contentEquals(resolutionType)) {
      resolutionPath = "0.jpg";
    } else if("M".contentEquals(resolutionType)) {
      resolutionPath = "sddefault.jpg";
    } else if("H".contentEquals(resolutionType)) {
      resolutionPath = "maxresdefault.jpg";
    }

    StringBuilder  stb = new StringBuilder();
    stb.append(imgYoutubeUrl);
    stb.append(path);
    stb.append("/"+videoId);
    stb.append(resolutionPath);

    return stb.toString();
  }
}
