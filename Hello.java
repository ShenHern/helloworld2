class QuickStart {
    public static void main (String[] args) {
        String noise = Animal.bark();
        System.out.println(noise);

        int mynumber = 0;
        
        for (int i = 0; i < 12; i++){
            mynumber++;
            if(mynumber == 12){
                System.out.println("This number is 12ish");
                break;
            }
            System.out.println(mynumber);
        }
    }
}