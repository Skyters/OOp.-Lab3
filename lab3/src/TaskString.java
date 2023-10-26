public class TaskString {
    private String text;
    public TaskString(String text) {this.text = text;}
    public TaskString() {this.text = "";}

    public String getString(){
        return this.text;
    }

    public void deleteThreeWord() {
        if (text.isEmpty()) {
            System.out.println( "(Ошибка! Введён пустой текст)") ;
        }

        StringBuilder str = new StringBuilder();

        int wordNum = 1;
        for(char ch : text.toCharArray())
        {
            if(wordNum % 3 != 0)
            {
                str.append(Character.toString(ch));
            }

            if (ch == ' ')
            {
                wordNum++;
            }
        }
        System.out.println("Изначальная строка: ");
        System.out.println(this.text);
        System.out.println("Изменённая строка ");
        System.out.println(str.toString());
    }
}

