using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class Posts
    {
        public Posts()
        {
            Comments = new HashSet<Comments>();
            Pictures = new HashSet<Pictures>();
            PostFoodCategory = new HashSet<PostFoodCategory>();
        }

        public int PostId { get; set; }
        public int? AccountId { get; set; }
        public string PostName { get; set; }
        public string Content { get; set; }
        public string Address { get; set; }

        public virtual Accounts Account { get; set; }
        public virtual ICollection<Comments> Comments { get; set; }
        public virtual ICollection<Pictures> Pictures { get; set; }
        public virtual ICollection<PostFoodCategory> PostFoodCategory { get; set; }
    }
}
