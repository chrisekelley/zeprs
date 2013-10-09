package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentsListTest extends ZeprsTest {

    public void testCommentsList() {

        // here's the line I added to make the logging output work ...
        //PropertyConfigurator.configure(LOGFILE);
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Long patientId = new Long("1");
        Long problemId = new Long("1");
        List comments = null;
        try {
             comments = CommentDAO.getAllforProblemFullname(conn, patientId, problemId);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get comments");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get comments");
        }
        assertNotNull("comments was null", comments);
    }

    public void testComment() {
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        // here's the line I added to make the logging output work ...
        //PropertyConfigurator.configure(LOGFILE);

        Long patientId = new Long("1");
        Boolean status = Boolean.TRUE;
        Comment comment = null;
        try {
             comment = (Comment) CommentDAO.getOne(conn, patientId, status);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get comments");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get comments");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull("comment was null", comment);
    }
}
