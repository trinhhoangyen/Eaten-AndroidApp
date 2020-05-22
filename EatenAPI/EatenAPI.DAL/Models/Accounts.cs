using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class Accounts
    {
        public Accounts()
        {
            Comments = new HashSet<Comments>();
            Posts = new HashSet<Posts>();
        }

        public int AccountId { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string DisplayName { get; set; }
        public string AvatarURL { get; set; }
        public string Gender { get; set; }
        public int? YearOfBirth { get; set; }

        public virtual ICollection<Comments> Comments { get; set; }
        public virtual ICollection<Posts> Posts { get; set; }
    }
}
