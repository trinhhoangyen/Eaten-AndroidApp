using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL.ViewModels
{
    public class PostInfoViewModel
    {
        public int PostId { get; set; }
        public int? AccountId { get; set; }
        public string PostName { get; set; }
        public string Content { get; set; }
        public string Address { get; set; }
        public string Picture { get; set; }
        public string DisplayName { get; set; }
        public int ReactQuantity { get; set; }
    }
}
