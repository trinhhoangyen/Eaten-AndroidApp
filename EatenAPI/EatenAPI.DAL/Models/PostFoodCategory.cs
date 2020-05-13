using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class PostFoodCategory
    {
        public int PostId { get; set; }
        public int CategoryId { get; set; }

        public virtual FoodCategories Category { get; set; }
        public virtual Posts Post { get; set; }
    }
}
