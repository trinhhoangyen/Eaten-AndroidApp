using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class TheLoai
    {
        public TheLoai()
        {
            BaiDangTheLoai = new HashSet<BaiDangTheLoai>();
        }

        public int MaLoai { get; set; }
        public string TenLoai { get; set; }

        public virtual ICollection<BaiDangTheLoai> BaiDangTheLoai { get; set; }
    }
}
