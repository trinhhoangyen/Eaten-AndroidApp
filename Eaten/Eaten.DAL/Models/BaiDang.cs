using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class BaiDang
    {
        public BaiDang()
        {
            BaiDangTheLoai = new HashSet<BaiDangTheLoai>();
            HinhAnh = new HashSet<HinhAnh>();
            TuongTac = new HashSet<TuongTac>();
        }

        public int MaBai { get; set; }
        public int? MaTaiKhoan { get; set; }
        public string TenBai { get; set; }
        public string NoiDung { get; set; }

        public virtual TaiKhoan MaTaiKhoanNavigation { get; set; }
        public virtual ICollection<BaiDangTheLoai> BaiDangTheLoai { get; set; }
        public virtual ICollection<HinhAnh> HinhAnh { get; set; }
        public virtual ICollection<TuongTac> TuongTac { get; set; }
    }
}
