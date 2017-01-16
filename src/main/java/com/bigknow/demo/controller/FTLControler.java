package com.bigknow.demo.controller;

import com.bigknow.demo.service.IFtlService;
import com.bigknow.frame.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.bigknow.demo.model.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhan on 2016/11/9 0009.
 */
@RestController
@RequestMapping("/demo/ftl")
public class FTLControler extends BaseRestController<Book>{

    @Autowired
    private IFtlService ftlService;

    @RequestMapping(value = "/demoA", method = RequestMethod.GET)
    public ModelAndView demoA() {
        Book book = new Book();
        book.setBookId("A1111");
        book.setAuthor("JK");
        book.setBookName("Harry");
        book.setPrice(new BigDecimal(15));
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        bookList.add(book);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("book",book);
        map.put("bookList",bookList);
        return new ModelAndView("/demo/demo", map);
        //return new ModelAndView("/demo/demo", "book",book);
    }

    @RequestMapping(value = "/demoB", method = RequestMethod.POST)
    public ModelAndView demoB(Book book) {
        System.out.println(book);
        return new ModelAndView("/demo/suc", "book",book);
    }
}
