package Group2A.SDMS.Controllers;

import Group2A.SDMS.Authentication.PrincipalUser;
import Group2A.SDMS.Entities.Products;
import Group2A.SDMS.Entities.ProductCategories;
import Group2A.SDMS.Entities.Users;
import Group2A.SDMS.Services.CategoryService;
import Group2A.SDMS.Services.ProductService;
import Group2A.SDMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Controller

public class AdminController {

    // ----------------------------User--------------------------------------------

    // User has been moved to its own controller (thanks, the one dev army ;) )

    @Autowired
    CategoryService categoryService;




    // ----------------------------Products--------------------------------------------



}
