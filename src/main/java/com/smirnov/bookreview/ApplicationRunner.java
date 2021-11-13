package com.smirnov.bookreview;

import com.smirnov.bookreview.config.SpringJdbcConfig;
import com.smirnov.bookreview.dao.impl.BookDaoImpl;
import com.smirnov.bookreview.dao.impl.ReviewDaoImpl;
import com.smirnov.bookreview.dao.impl.UserDaoImpl;
import com.smirnov.bookreview.models.Book;
import com.smirnov.bookreview.models.Image;
import com.smirnov.bookreview.models.Review;
import com.smirnov.bookreview.models.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;

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
        Calendar c = Calendar.getInstance();

        Review review = new Review();
        review.setRating(5);
        review.setBookReview("some review");
        review.setDate(c);
        review.setUserId(11);
        review.setBookId(6);

        //System.out.println(review);
        ReviewDaoImpl reviewDao = context.getBean("reviewDaoImpl", ReviewDaoImpl.class);
        reviewDao.save(review);

        context.close();
    }
}
