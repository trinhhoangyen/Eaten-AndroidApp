using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class TaiKhoan
    {
        public TaiKhoan()
        {
            BaiDang = new HashSet<BaiDang>();
            TuongTac = new HashSet<TuongTac>();
        }

        public int MaTaiKhoan { get; set; }
        public string Email { get; set; }
        public string MatKhau { get; set; }
        public string TenHienThi { get; set; }
        public byte[] Avatar { get; set; }
        public string GioiTinh { get; set; }
        public int? NamSinh { get; set; }

        public virtual ICollection<BaiDang> BaiDang { get; set; }
        public virtual ICollection<TuongTac> TuongTac { get; set; }
    }
}
