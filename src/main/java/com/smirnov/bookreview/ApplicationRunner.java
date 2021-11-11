package com.smirnov.bookreview;

import com.smirnov.bookreview.config.SpringJdbcConfig;
import com.smirnov.bookreview.dao.impl.BookDaoImpl;
import com.smirnov.bookreview.dao.impl.UserDaoImpl;
import com.smirnov.bookreview.models.Book;
import com.smirnov.bookreview.models.Image;
import com.smirnov.bookreview.models.Review;
import com.smirnov.bookreview.models.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

        /*User user = User.builder().withName("Mark Smith")
                .withEmail("someEmail@gmail.com").withPassword("12345")
                .withType("Moderator").build();
        //Book book = Book.builder().withId(1).withName("Some name").build();
        Image image = Image.builder().withId(1).build();
        Review review = Review.builder().withId(1).build();

        UserDaoImpl userDao = context.getBean("userDaoImpl", UserDaoImpl.class);
        System.out.println(userDao.findAll());*/
        BookDaoImpl bookDao = context.getBean("bookDaoImpl", BookDaoImpl.class);
        System.out.println(bookDao.findById(1).get());

        context.close();
    }
}
