//package com.example.demo.Adapter;
//
//import com.example.demo.ApplicationContextHolder;
//import com.example.demo.Service.BookService;
//import com.example.demo.models.Book;
//import com.example.demo.models.Thing;
//import com.example.demo.repository.BookRepository;
//
//public class ThingAdapter extends RentalObject{
//    private Thing thing;
//
//
//    public ThingAdapter(Thing thing){
//        this.thing = thing;
//    }
//
//    @Override
//    public void save(){
//        if (thing.getType().equals("book")){
//            BookService bookService = ApplicationContextHolder.getContext().getBean(BookService.class);
//
//            bookService.save((Book)thing);
//        }
//    }
//}
