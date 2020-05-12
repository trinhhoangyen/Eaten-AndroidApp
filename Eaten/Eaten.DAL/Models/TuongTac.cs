using System;
using System.Collections.Generic;

namespace Eaten.DAL.Models
{
    public partial class TuongTac
    {
        public int MaBinhLuan { get; set; }
        public int MaBai { get; set; }
        public int MaTaiKhoan { get; set; }
        public string NoiDung { get; set; }
        public int? CamXuc { get; set; }
        public int? DanhGia { get; set; }

        public virtual BaiDang MaBaiNavigation { get; set; }
        public virtual TaiKhoan MaTaiKhoanNavigation { get; set; }
    }
}
