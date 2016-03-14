package dc4lab;

/**
 * Created by Proggeo on 3/12/2016.
 */
class Data {
    String firstName;
    String lastName;
    String number;

    Data(String datainput) {
        int i = datainput.indexOf(" ");
        firstName = datainput.substring(0, i);
        int j = datainput.indexOf(":");
        lastName = datainput.substring(i + 1, j);
        number = datainput.substring(j + 1, datainput.length());
        //print();
    }

    String print() {
        String result = "";
        result = result.concat("Name: " + firstName);
        result = result.concat("\nSurname: " + lastName);
        result = result.concat("\nNumber: " + number);
        return result;
    }

}
