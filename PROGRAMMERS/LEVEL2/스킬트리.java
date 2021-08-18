public class 스킬트리 {

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i = 0;i < skill_trees.length; i++){
            answer += isOK(skill, skill_trees[i]);
        }

        return answer;
    }

    private static int isOK(String skill, String skill_tree) {

        String unit = "";

        for(int i = 0;i < skill_tree.length(); i++){
            if(skill.indexOf(skill_tree.charAt(i)) != -1){
                unit += skill_tree.charAt(i);
            }
        }

        if(skill.indexOf(unit) == 0)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        String[] t = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution("CBD", t));
    }
}
