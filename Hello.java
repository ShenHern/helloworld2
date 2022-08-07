class QuickStart {
    public static void main (String[] args) {
        Animal a = new Animal();
        String noise = a.bark();
        System.out.println(noise);

        int mynumber = 0;
        
        for (int i = 0; i < 12; i++){
            mynumber++;
            System.out.println(mynumber + "\n");
        }
    }
}