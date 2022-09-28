import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Main main = new Main();
        main.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});


    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];

        dfs(begin, target, words, visited, 0);
        System.out.println(answer);
        return answer;
    }

    void dfs(String begin,String target, String[] words,boolean[] visited,int cnt){
        if(begin.equals(target)){
            System.out.println(cnt);
            return;
        }
    }

}
