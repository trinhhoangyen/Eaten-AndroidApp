using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL.ViewModels
{
    public class CommentViewModel
    {
        public int PostId { get; set; }
        public int AccountId { get; set; }
        public string AvatarURL { get; set; }
        public string DisplayName { get; set; }
        public string Content { get; set; }
    }
}
