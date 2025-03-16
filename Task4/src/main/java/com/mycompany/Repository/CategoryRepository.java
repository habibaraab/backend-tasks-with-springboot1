package com.mycompany.Repository;

import com.mycompany.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

        public interface CategoryRepository extends JpaRepository<Category, Long> {
}
