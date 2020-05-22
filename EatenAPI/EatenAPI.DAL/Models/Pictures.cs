using System;
using System.Collections.Generic;

namespace EatenAPI.DAL.Models
{
    public partial class Pictures
    {
        public int PictureId { get; set; }
        public int? PostId { get; set; }
        public string PictureURL { get; set; }

        public virtual Posts Post { get; set; }
    }
}
