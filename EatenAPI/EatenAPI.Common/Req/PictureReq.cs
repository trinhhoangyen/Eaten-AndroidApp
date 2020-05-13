using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.Common.Req
{
    public class PictureReq
    {
        public int PictureId { get; set; }
        public int? PostId { get; set; }
        public byte[] Picture { get; set; }
    }
}
