using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class HinhAnh
    {
        public int MaHinh { get; set; }
        public int? MaBai { get; set; }
        public byte[] Hinh { get; set; }

        public virtual BaiDang MaBaiNavigation { get; set; }
    }
}
