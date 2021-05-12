public class Strings{
	public static int spacesInString(String str){
		int spaces = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == ' '){
				spaces++;
			}
		}
		return spaces;
	}
	
	public static String separarString(String str, int op){
		int spacePos[] = new int[spacesInString(str)];
		
		for(int i = 0, j = 0; i < str.length(); i++){
			if(str.charAt(i) == ' '){
				spacePos[j] = i;
				j++;
			}
		}
		
		if(op > spacesInString(str)){
			return str.substring(spacePos[op - 2] + 1, str.length()).toUpperCase();
		}
		else if(op > 1){
			return str.substring(spacePos[op - 2] + 1, spacePos[op - 1]).toUpperCase();
		}
		else{
			return str.substring(0, spacePos[0]).toUpperCase();
		}
	}
}