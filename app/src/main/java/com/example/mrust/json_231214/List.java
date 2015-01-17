package com.example.mrust.json_231214;


public class List {
    Event first,last = new Event();
    List(){
        first = null;
        last = null;
    }

    public void add(Event event)
    {
        if( first == null ) {
            first = event;
            last = event;
        }
        else
        {
            first.next = event;
            last = event;
        }
    }
    // [TODO] liste ve event yapısını kullanarak ornek bir proje yap bu projede birden fazla degişken için tek tek degerlerini doldurcakmı diye incele
    /*
    * example source
    * Liste liste = new Liste();
    * Event event = new Event();
    * event.set....
    * event.set....
    * liste.add(event);*/

}
