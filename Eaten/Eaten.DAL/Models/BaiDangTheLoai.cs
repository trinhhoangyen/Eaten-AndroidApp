using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class BaiDangTheLoai
    {
        public int MaBai { get; set; }
        public int MaLoai { get; set; }

        public virtual BaiDang MaBaiNavigation { get; set; }
        public virtual TheLoai MaLoaiNavigation { get; set; }
    }
}
