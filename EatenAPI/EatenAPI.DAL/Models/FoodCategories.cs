using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class FoodCategories
    {
        public FoodCategories()
        {
            PostFoodCategory = new HashSet<PostFoodCategory>();
        }

        public int CategoryId { get; set; }
        public string CategoryName { get; set; }

        public virtual ICollection<PostFoodCategory> PostFoodCategory { get; set; }
    }
}
