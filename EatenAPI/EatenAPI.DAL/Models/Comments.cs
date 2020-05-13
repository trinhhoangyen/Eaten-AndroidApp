using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class Comments
    {
        public int CommentId { get; set; }
        public int PostId { get; set; }
        public int AccountId { get; set; }
        public string Content { get; set; }
        public int? CamXuc { get; set; }
        public int? DanhGia { get; set; }

        public virtual Accounts Account { get; set; }
        public virtual Posts Post { get; set; }
    }
}
